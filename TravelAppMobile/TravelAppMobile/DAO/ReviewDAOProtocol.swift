//
//  ReviewDAOProtocol.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 25/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

protocol ReviewDAOProtocol {
    
    func getUserByReviewID(id: Int) -> User?
    
    func addReview(title: String, body: String, points: Int, userID: Int, structureID: Int, completion: @escaping ([String: Any]) -> Void)
}
