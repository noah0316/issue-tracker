//
//  ConvertHexToUIColor.swift
//  IssueTracker
//
//  Created by ilim on 2023/05/24.
//

import UIKit

enum HexConverter {
    static func toUIColor(_ hex: String) -> UIColor? {
        var hexString = hex.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()
        var rgb: UInt32 = 0

        if hexString.hasPrefix("#") { hexString.remove(at: hexString.startIndex) }
        
        Scanner(string: hexString).scanHexInt32(&rgb)
        
        return UIColor(
            red: CGFloat((rgb & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((rgb & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(rgb & 0x0000FF) / 255.0,
            alpha: 1.0
        )
    }
}
