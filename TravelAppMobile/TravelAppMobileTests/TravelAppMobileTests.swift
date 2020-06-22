//
//  TravelAppMobileTests.swift
//  TravelAppMobileTests
//
//  Created by Piero Junior Gaetani on 22/06/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import XCTest
@testable import TravelAppMobile

class TravelAppMobileTests: XCTestCase {

    /**
     Tests the approximation (excess or default)) of a rating from any float in the range of 0 - 5 to the same range but with 0.5 point increments (0.5, 1, 1.5, 2, ecc...).
     */
    func testStarsTranslation() throws {
        let testCases : [(testValue: Float, result: Float)] = [(2.3, 2.5), (2.9, 3), (2.8, 3)]
        for (index, testCase) in testCases.enumerated() {
            XCTAssert(GeneralReusables.avgPointsApproximation(points: testCase.testValue) == testCase.result, "failed testcase at index \(index)")

        }
    }

    /**
     Tests if the assets names the app tries to access are correct
    */
    func testStarImageNames() throws {
        let testCases : [(testValue: Float, result: String)] = [(0, "0stars.pdf"), (0.4, "0,5stars.pdf"), (0.9, "1stars.pdf")]
        for (index, testCase) in testCases.enumerated() {
            XCTAssert(GeneralReusables.starsImageAssetName(avgPoints: testCase.testValue) == testCase.result, "failed testcase at index \(index)")
        }
    }

}
