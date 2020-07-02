//
//  User.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 15/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

struct User: Decodable, Hashable {
    let id : Int
    let firstName : String?
    let lastName : String?
    let gender : String?
    let image : String?
    let username : String?
    let email:  String?
    let password : String?
    //let createdAt
    //let updatedAt
    
    var imageDownloaded: UIImageCodable?
    
    var token : String?
}

