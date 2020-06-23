//
//  Structure.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 21/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

import Foundation

struct Structure: Decodable, Hashable {
    
    let id : Int
    let name:  String
    let place : String?
    let category : String?
    let price : Int?
    let webSite : String?
    let contacts : String?
    let description : String?
    let image : String?
    let nReviews : Int?
    let avgPoints : Float?
    
    var imageDownloaded: UIImageCodable?
}

