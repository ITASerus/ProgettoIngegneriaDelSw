//
//  HomeController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 15/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class HomeController {
    private static let structureDAO = DAOFactory.getStructureDAO(type: "AWSElasticBeanstalk")

    func getStructureByID(id: Int) -> Structure {
        return HomeController.structureDAO.getByID(id: id)!
    }
    
    func getAllStructuresWithAvgPoints() -> [Structure] {
        return HomeController.structureDAO.getAllStructuresWithAvgPoints()!
    }
}
