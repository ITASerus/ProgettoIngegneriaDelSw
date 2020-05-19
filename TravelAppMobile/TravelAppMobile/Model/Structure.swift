//
//  Structure.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 15/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

class Structure: Codable {
    var  id : Int
    var  name:  String
    var  place : String
    var  category : String
    var  price : Int
    var  webSite : String?
    var  contacts : String
    var  tag : String?
    var  description : String
    var  image : String
    
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
