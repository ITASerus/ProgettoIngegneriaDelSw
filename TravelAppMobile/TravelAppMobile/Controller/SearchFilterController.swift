//
//  SearchFilterController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 23/06/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class SearchFilterController {
    private static let structureDAO = DAOFactory.getStructureDAO(type: "AWSElasticBeanstalk")
    
    func getStructureByFilter(name: String, place: String, category: String, contacts: String, webSite: String, lowerPrice: String, upperPrice: String, avgPoints: String) -> [Structure] {
        return SearchFilterController.structureDAO.getStructuresByFilter(name: name, place: place, category: category, contacts: contacts, webSite: webSite, lowerPrice: lowerPrice, upperPrice: upperPrice, avgPoints: avgPoints)!
    }
}
