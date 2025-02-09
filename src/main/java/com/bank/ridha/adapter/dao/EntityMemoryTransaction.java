package com.bank.ridha.adapter.dao;

import java.util.Optional;

public interface EntityMemoryTransaction<EntityType, IdType> {

    EntityType create(EntityType entity);

    EntityType update(EntityType entity);

    void delete(IdType id);

    Optional<EntityType> find(IdType id);
}
