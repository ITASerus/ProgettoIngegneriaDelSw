//
//  Review.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 15/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

struct Review: Decodable, Hashable {
    
    let id : Int
    let title:  String
    let description : String?
    let points : Float?
    //let date : Date?
    let structureID: Int
    let userID: Int
    let firstName: String?
    let image: String?
    
    var imageDownloaded: UIImageCodable?
}
