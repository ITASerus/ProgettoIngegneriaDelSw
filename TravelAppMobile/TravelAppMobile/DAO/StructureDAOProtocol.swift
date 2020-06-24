//
//  StructureDAO.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 21/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

protocol StructureDAOProtocol {
    
    func getByID(id: Int) -> Structure?
    
    func getAllStructures() -> [Structure]?
    
    func getAllStructuresWithAvgPoints() -> [Structure]?
    
    func getStructuresByFilter(name: String, place: String, category: String, contacts: String, webSite: String, lowerPrice: String, upperPrice: String, avgPoints: String) -> [Structure]?
    
    func getReviewsByStructureID(id: Int) -> [Review]?
    func getReviewsWUserInfoByStructureID(id: Int) -> [Review]?
}
