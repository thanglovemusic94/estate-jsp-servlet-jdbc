package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.BuildingEntity;

public interface IBuildingRepository extends GennericJDBC<BuildingEntity> {

	Long insert(BuildingEntity buildingEntity);

}
