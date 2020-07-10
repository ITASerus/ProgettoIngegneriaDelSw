//
//  ProfileController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 09/07/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class ProfileController {
    private static let userDAO = DAOFactory.getUserDAO(type: "AWSElasticBeanstalk")
    private static let structureDAO = DAOFactory.getStructureDAO(type: "AWSElasticBeanstalk")
    
    func getReviews(userID: Int) -> [Review] {
        return ProfileController.userDAO.getReviewsWStructureInfoByUserID(userID: userID)!
    }
}
