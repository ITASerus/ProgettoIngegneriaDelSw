//
//  User.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 15/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

struct Users: Codable {
  
    
  let  id : Int
  let  email:  String?
  let  username : String?
  let  password : String?
  let  registrationDate : String?
    var  realName : String?
    var  realSurname : String?
  let  viewRealName : Bool?
  let  role : String?
  let  image : Array<Int>?

}

