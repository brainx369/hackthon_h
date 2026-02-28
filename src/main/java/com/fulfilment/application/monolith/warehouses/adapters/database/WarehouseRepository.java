package com.fulfilment.application.monolith.warehouses.adapters.database;

import com.fulfilment.application.monolith.warehouses.domain.models.Warehouse;
import com.fulfilment.application.monolith.warehouses.domain.ports.WarehouseStore;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class WarehouseRepository implements WarehouseStore, PanacheRepository<DbWarehouse> {

  @Override
  public List<Warehouse> getAll() {
    return this.listAll().stream().map(DbWarehouse::toWarehouse).toList();
  }

  @Override
  public void create(Warehouse warehouse) {
    DbWarehouse dbWarehouse = new DbWarehouse();
    dbWarehouse.businessUnitCode = warehouse.businessUnitCode;
    dbWarehouse.location = warehouse.location;
    dbWarehouse.capacity = warehouse.capacity;
    dbWarehouse.stock = warehouse.stock;
    dbWarehouse.createdAt = warehouse.createdAt;
    dbWarehouse.archivedAt = warehouse.archivedAt;
    
    this.persist(dbWarehouse);
  }

  /*@Override
  public void update(Warehouse warehouse) {
    getEntityManager().createQuery(
      "UPDATE DbWarehouse w SET w.location = :loc, w.capacity = :cap, " +
      "w.stock = :stock, w.archivedAt = :archived WHERE w.businessUnitCode = :code")
      .setParameter("loc", warehouse.location)
      .setParameter("cap", warehouse.capacity)
      .setParameter("stock", warehouse.stock)
      .setParameter("archived", warehouse.archivedAt)
      .setParameter("code", warehouse.businessUnitCode)
      .executeUpdate();

    // Clear persistence context to see updates in subsequent queries
    getEntityManager().flush();
    getEntityManager().clear();
  }*/

    @Override
    @Transactional
    public void update(Warehouse warehouse) {

        DbWarehouse db = find("businessUnitCode", warehouse.businessUnitCode)
                .firstResult();

        if (db == null) {
            throw new RuntimeException("Warehouse not found");
        }

        db.location = warehouse.location;
        db.capacity = warehouse.capacity;
        db.stock = warehouse.stock;
        db.archivedAt = warehouse.archivedAt;

        // NO manual update query
        // NO flush
        // NO clear
        // Hibernate will automatically check version on commit
    }

  @Override
  public void remove(Warehouse warehouse) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }

  @Override
  public Warehouse findByBusinessUnitCode(String buCode) {
    DbWarehouse dbWarehouse = find("businessUnitCode", buCode).firstResult();
    return dbWarehouse != null ? dbWarehouse.toWarehouse() : null;
  }
}
