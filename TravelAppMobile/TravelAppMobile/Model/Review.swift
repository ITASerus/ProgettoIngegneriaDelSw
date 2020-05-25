//
//  Review.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 15/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation


// DA CONVERTIRE IN STRUCT. AL MOMENTO E' UNA CLASS PERCHE' MI SERVIVANO LE CAPACITA' DI UNA CLASS PER FINGERE I DATI. PRENDERE SPUNTO DALLA STRUCT 'STRUCTURE'

class Review: Codable {
    var  id : Int
    var  title: String
    var  description:  String
    var  points : Int
    var  date : String
    var  author : String
    
    init(id: Int, title: String, description: String, points: Int, date: String, author: String) {
        self.id = id
        self.title = title
        self.description = description
        self.points = points
        self.date = date
        self.author = author
    }
}
