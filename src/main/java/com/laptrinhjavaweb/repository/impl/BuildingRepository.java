package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.IBuildingRepository;

public class BuildingRepository extends AbstractJDBC<BuildingEntity> implements IBuildingRepository {

	@Override
	public Long insert(BuildingEntity buildingEntity) {
		StringBuilder sql = new StringBuilder(
				"INSERT INTO building (name,ward,street,numberofbasement,buildingarea,district,structure,costrent,costdescription,servicecost,");
		sql.append(
				"carcost,motobikecost,overtimecost,electricitycost,deposit,payment,timerent,timedecorator,managername,managerphone,type");
		sql.append(",createdby,modifiedby,createddate,modifieddate");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		return this.insert(sql.toString(), buildingEntity.getName(), buildingEntity.getWard(),
				buildingEntity.getStreet(), buildingEntity.getNumberOfBasement(), buildingEntity.getBuildingArea(),
				buildingEntity.getDistrict(), buildingEntity.getStructure(), buildingEntity.getCostRent(),
				buildingEntity.getCostDescription(), buildingEntity.getServiceCost(), buildingEntity.getCarCost(),
				buildingEntity.getMotobikeCost(), buildingEntity.getOvertimeCost(), buildingEntity.getElectricityCost(),
				buildingEntity.getDeposit(), buildingEntity.getPayment(), buildingEntity.getTimeRent(),
				buildingEntity.getTimeDecorator(), buildingEntity.getManagerName(), buildingEntity.getManagerPhone(),
				buildingEntity.getType(), buildingEntity.getCreatedBy(), buildingEntity.getModifiedBy(),
				buildingEntity.getCreatedDate(), buildingEntity.getModifiedDate());
	}

}
