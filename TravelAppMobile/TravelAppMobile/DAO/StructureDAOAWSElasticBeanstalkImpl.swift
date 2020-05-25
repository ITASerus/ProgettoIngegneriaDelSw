//
//  StructureDAOAWSElasticBeanstalkImpl.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 21/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class StructureDAOAWSElasticbeanstalkImpl: StructureDAOProtocol {
    
    private let GET_BY_ID = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/id="
    private let GET_ALL_STRUCTURES = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/getAll"
    private let GET_ALL_WITH_AVG_POINTS = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/getAllWithAvgPoints"
    
    func getByID(id: Int) -> Structure? {
        let dataStructures = try! Data.init(contentsOf: URL.init(string: GET_BY_ID + String(id))!)
        
        do {
            let decoder: JSONDecoder = JSONDecoder.init()
            let structure: Structure = try decoder.decode(Structure.self, from: dataStructures)
                        
            return structure
        } catch let e {
            print(e)
        }

        return nil
    }
    
    func getAllStructures() -> [Structure]? {
        let dataStructures = try! Data.init(contentsOf: URL.init(string: GET_ALL_STRUCTURES)!)
        
        do {
            let decoder: JSONDecoder = JSONDecoder.init()
            let structure: [Structure] = try decoder.decode([Structure].self, from: dataStructures)
            
            return structure
        } catch let e {
            print(e)
        }

        return nil
    }
    
    func getAllStructuresWithAvgPoints() -> [Structure]? {
           let dataStructures = try! Data.init(contentsOf: URL.init(string: GET_ALL_WITH_AVG_POINTS)!)
           
           do {
               let decoder: JSONDecoder = JSONDecoder.init()
               let structure: [Structure] = try decoder.decode([Structure].self, from: dataStructures)
               
               return structure
           } catch let e {
               print(e)
           }

           return nil
       }
}
