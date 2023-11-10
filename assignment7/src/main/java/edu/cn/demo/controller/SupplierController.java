package edu.cn.demo.controller;


import edu.cn.demo.domain.Supplier;
import edu.cn.demo.service.impl.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierServiceImpl supplierService;


    //添加供应商
    @PostMapping("")
    @PreAuthorize("hasAuthority('supplier/admin')")
    public ResponseEntity<Supplier> add(@RequestBody Supplier supplier){
        supplierService.add(supplier);
        return ResponseEntity.ok(supplier);
    }

    //修改供应商信息
    @PutMapping("")
    @PreAuthorize("hasAuthority('supplier/admin')")
    public ResponseEntity<Void> change(@RequestBody Supplier supplier){
        if(supplierService.change(supplier))
            return ResponseEntity.ok().build();
        else
            //修改失败，不存在对应id的供应商
            return ResponseEntity.noContent().build();
    }

    //删除商品
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('supplier/admin')")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if(supplierService.delete(id))
            return ResponseEntity.ok().build();
        else
            //删除失败，不存在对应id的供应商
            return ResponseEntity.noContent().build();
    }

    //通过id查找
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('supplier/query')")
    public ResponseEntity<Supplier> getById(@PathVariable int id){
        Supplier result = supplierService.searchById(id);
        if(result==null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(result);
    }

    //通过供应商名称查找
    @GetMapping("/name")
    @PreAuthorize("hasAuthority('supplier/query')")
    public ResponseEntity<Supplier> getByName(String name){
        Supplier result = supplierService.searchByName(name);
        if(result==null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(result);
    }

    //通过供应商地址查找
    @GetMapping("/address")
    @PreAuthorize("hasAuthority('supplier/query')")
    public ResponseEntity<Supplier> getByAddress(String address){
        Supplier result = supplierService.searchByAddress(address);
        if(result==null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(result);
    }

}

