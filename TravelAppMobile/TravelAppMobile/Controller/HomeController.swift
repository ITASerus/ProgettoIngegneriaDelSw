//
//  HomeController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 15/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation
import CoreLocation
import MapKit

public class HomeController {
    private static let structureDAO = DAOFactory.getStructureDAO(type: "AWSElasticBeanstalk")
    var userPlace: CLPlacemark!
    
    func getUserPlace() -> CLPlacemark {
        return userPlace;
    }
    func setUserPlace(userPlace: CLPlacemark) {
        self.userPlace = userPlace;
    }

    func getStructureByFilter(name: String, place: String, category: String, contacts: String, webSite: String, lowerPrice: String, upperPrice: String, avgPoints: String) -> [Structure] {
        return HomeController.structureDAO.getStructuresByFilter(name: name, place: place, category: category, contacts: contacts, webSite: webSite, lowerPrice: lowerPrice, upperPrice: upperPrice, avgPoints: avgPoints)!
    }
    
    func getStructureByID(id: Int) -> Structure {
        return HomeController.structureDAO.getByID(id: id)!
    }
    
    func getAllStructuresWithAvgPoints() -> [Structure] {
        return HomeController.structureDAO.getAllStructuresWithAvgPoints()!
    }    
}
