//
//  AddReviewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 13/07/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class AddReviewController {
    private static let reviewDAO = DAOFactory.getReviewDAO(type: "AWSElasticBeanstalk")
    
    func addReview(title: String, body: String, points: Int, userID: Int, structureID: Int, completion: @escaping (Int) -> Void) {
        AddReviewController.reviewDAO.addReview(title: title, body: body, points: points, userID: userID, structureID: structureID) { (responseJSON) in
            
            print(responseJSON)
            
            if(responseJSON["status"] != nil) {
                print("Errore")
                completion(1)
            } else {
                print("OK")
                completion(0)
            }
        }
    }
}
