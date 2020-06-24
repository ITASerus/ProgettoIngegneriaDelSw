//
//  ReviewDAOAWSElasticBeanstalkImpl.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 25/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class ReviewDAOAWSElasticbeanstalkImpl: ReviewDAOProtocol {
    private let GET_USER_BY_REVIEW_ID = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/reviews/id="
    
    func getUserByReviewID(id: Int) -> User? {
        let FINAL_URL = GET_USER_BY_REVIEW_ID + id.description + "/getUser"
        
        let dataStructures = try! Data.init(contentsOf: URL.init(string: FINAL_URL)!)
        
        do {
            let decoder: JSONDecoder = JSONDecoder.init()
            let user: User = try decoder.decode(User.self, from: dataStructures)
                        
            return user
        } catch let e {
            print(e)
        }

        return nil
    }
}
