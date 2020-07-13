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
    private let GET_BY_FILTER = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/search/"
    private let GET_REVIEWS_BY_STRUCTURE_ID = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/id="
    
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
            let structures: [Structure] = try decoder.decode([Structure].self, from: dataStructures)
            
            return structures
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

    func getStructuresByFilter(name: String, place: String, category: String, contacts: String, webSite: String, lowerPrice: String, upperPrice: String, avgPoints: String) -> [Structure]? {
        
        let stringName : String = "name=" + (name.isEmpty ? "null" : name)
        let stringPlace : String = "&place=" + (place.isEmpty ? "null" : place)
        let stringCategory : String = "&category=" + ((category.isEmpty || category == "---") ? "null" : category)
        let stringContacts : String = "&contacts=" + (contacts.isEmpty ? "null" : contacts)
        let stringWebSite : String = "&webSite=" + (webSite.isEmpty ? "null" : webSite)
        let stringLowerPrice : String = "&lowerPrice=" + (lowerPrice.isEmpty ? "-1" : lowerPrice)
        let stringUpperPrice : String = "&upperPrice=" + (upperPrice.isEmpty ? "-1" : upperPrice)
        let stringAvgPoints : String = "&avgPoints=" + (avgPoints.isEmpty ? "null" : avgPoints)
       
        var FINAL_URL = GET_BY_FILTER + stringName + stringPlace + stringCategory + stringContacts + stringWebSite + stringLowerPrice + stringUpperPrice + stringAvgPoints
        
        FINAL_URL = FINAL_URL.replacingOccurrences(of: " ", with: "%20", options: .literal, range: nil)
        FINAL_URL = FINAL_URL.replacingOccurrences(of: "à", with: "a", options: .literal, range: nil) // Da implementare meglio ma per prototipo è accettabile
    
        let dataStructures = try! Data.init(contentsOf: URL.init(string: FINAL_URL)!)
        
        do {
            let decoder: JSONDecoder = JSONDecoder.init()
            let structure: [Structure] = try decoder.decode([Structure].self, from: dataStructures)
            
            return structure
        } catch let e {
            print(e)
        }
        
        return nil
    }
    
    func getReviewsByStructureID(id: Int) -> [Review]? {
        let FINAL_URL = GET_REVIEWS_BY_STRUCTURE_ID + id.description + "/getReviews"
        
        let dataStructures = try! Data.init(contentsOf: URL.init(string: FINAL_URL)!)
        
        do {
            let decoder: JSONDecoder = JSONDecoder.init()
            let reviews: [Review] = try decoder.decode([Review].self, from: dataStructures)

            return reviews
        } catch let e {
            print(e)
        }

        return nil
    }
    
    func getReviewsWUserInfoByStructureID(id: Int) -> [Review]? {
        let FINAL_URL = GET_REVIEWS_BY_STRUCTURE_ID + id.description + "/getReviewsWUserInfo"
        
        let dataStructures = try! Data.init(contentsOf: URL.init(string: FINAL_URL)!)
        
        do {
            let decoder: JSONDecoder = JSONDecoder.init()
            let reviews: [Review] = try decoder.decode([Review].self, from: dataStructures)

            return reviews
        } catch let e {
            print(e)
        }

        return nil
    }
}
