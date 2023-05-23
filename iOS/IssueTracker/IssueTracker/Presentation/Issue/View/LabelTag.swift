//
//  LabelTag.swift
//  IssueTracker
//
//  Created by ilim on 2023/05/22.
//

import UIKit

final class LabelTag: UILabel {
    private let edgeInsets = UIEdgeInsets(top: 4.0, left: 16.0, bottom: 4.0, right: 16.0)
    
    override var intrinsicContentSize: CGSize {
        var contentSize = super.intrinsicContentSize
        contentSize.width += edgeInsets.left + edgeInsets.right
        contentSize.height += edgeInsets.top + edgeInsets.bottom
        return contentSize
    }
    
    convenience init(name: String) {
        self.init()
        self.text = name
        self.font = FontStyle.label
        self.setLabelTagBackgroundColor("#c2b9a9")
        self.setLabelTagTextColor()
        self.sizeToFit()
    }
    
    override func drawText(in rect: CGRect) {
        super.drawText(in: rect.inset(by: self.edgeInsets))
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        self.layer.cornerRadius = ComponentStyle.Radius.regular
        self.layer.masksToBounds = true
    }
    
    func setLabelTagBackgroundColor(_ backgroundColor: String) {
        self.backgroundColor = UIColor().setBackgroundColorFromHex(backgroundColor)
    }
    
    func setLabelTagTextColor() {
        let rgb = self.backgroundColor?.cgColor.components?.dropLast() ?? [0, 0, 0]
        let multipliedRgb = rgb.map { $0 * 255 }
        if (multipliedRgb[0] * 0.299 + multipliedRgb[1] * 0.587 + multipliedRgb[2] * 0.114) > 186 {
            self.textColor = .black
        } else { self.textColor = .white }
    }
}

extension UIColor {
    func setBackgroundColorFromHex(_ hex: String) -> UIColor? {
        var hexString = hex.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()
        
        if hexString.hasPrefix("#") {
            hexString.remove(at: hexString.startIndex)
        }
        
        var rgb: UInt32 = 0
        Scanner(string: hexString).scanHexInt32(&rgb)
        
        return UIColor(red: CGFloat((rgb & 0xFF0000) >> 16) / 255.0,
                       green: CGFloat((rgb & 0x00FF00) >> 8) / 255.0,
                       blue: CGFloat(rgb & 0x0000FF) / 255.0,
                       alpha: 1.0)
    }
}
