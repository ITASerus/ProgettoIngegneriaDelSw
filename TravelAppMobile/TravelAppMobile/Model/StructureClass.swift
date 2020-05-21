//
//  Structure.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 15/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

class StructureClass: Codable {
    let  id : Int
    let  name:  String
    let  place : String
    let  category : String
    let  price : Int
    let  webSite : String?
    let  contacts : String
    let  tag : String?
    let  description : String
    let  image : String
    
    init(id: Int, name: String, place: String, category: String, price: Int, webSite: String, contacts: String, tag: String, description: String, image: String) {
        self.id = id
        self.name = name
        self.place = place
        self.category = category
        self.price = price
        self.webSite = webSite
        self.contacts = contacts
        self.tag = tag
        self.description = description
        self.image = image
    }
}
