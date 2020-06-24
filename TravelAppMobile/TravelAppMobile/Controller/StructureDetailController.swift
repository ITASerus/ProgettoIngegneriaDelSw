//
//  StructureDetailController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 15/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class StructureDetailController {
    private static let structureDAO = DAOFactory.getStructureDAO(type: "AWSElasticBeanstalk")
    private static let reviewDAO = DAOFactory.getReviewDAO(type: "AWSElasticBeanstalk")

    func getAllReviewsWUserInfo(structureId: Int) -> [Review] {
        return StructureDetailController.structureDAO.getReviewsWUserInfoByStructureID(id: structureId)!
    }
    
    func getUserOfReview(reviewId: Int) -> User {
        return StructureDetailController.reviewDAO.getUserByReviewID(id: reviewId)!
    }
}
