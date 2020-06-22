//
//  GeneralReusables.swift
//  TravelAppMobile
//
//  Created by Piero Junior Gaetani on 22/06/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

class GeneralReusables {
    /** Returns the string of the stars image file asset */
    static func starsImageAssetName(avgPoints points: Float) -> String {
        var imageName = ""
        let pointsApproximated = avgPointsApproximation(points: points)
        if pointsApproximated.truncatingRemainder(dividingBy: 1) == 0 {
            imageName = String(Int(pointsApproximated))
        } else {
            imageName = String(pointsApproximated).replacingOccurrences(of: ".", with: ",", options: .literal, range: nil)
        }
        imageName += "stars.pdf"
        
        return imageName
    }
    
    /** Approximates the rating to 0.5 point increments (to be used for star rapresentation) in the range of 0-5*/
    static func avgPointsApproximation(points: Float) -> Float{
        if points <= 5 {
            return round(points * 2) * 0.5
        } else {
            return 0
        }
    }
}
