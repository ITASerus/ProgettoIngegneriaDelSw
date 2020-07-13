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
    
    func addReview(title: String, body: String, points: Int, userID: Int, structureID: Int) {
        return AddReviewController.reviewDAO.addReview(title: title, body: body, points: points, userID: userID, structureID: structureID) { (responseJSON) in
            
            if(responseJSON["status"] != nil) {
                print("Errore")
            } else {
                print("OK")
            }
             print(responseJSON)
        }
    }
}
