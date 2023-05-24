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
        self.setLabelTagBackgroundColor(hexString: "#dbd9d9")
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
    
    func setLabelTagBackgroundColor(hexString: String) {
        self.backgroundColor = HexConverter.toUIColor(hexString)
    }
    
    func setLabelTagTextColor() {
        let rgb = self.backgroundColor?.cgColor.components?.dropLast() ?? [0, 0, 0]
        let multipliedRgb = rgb.map { $0 * Rgb.deNormalization }
        let weightedRed = multipliedRgb[0] * Rgb.red
        let weightedGreen = multipliedRgb[1] * Rgb.green
        let weightedBlue = multipliedRgb[2] * Rgb.blue
        let labelTextColorFormula = weightedRed + weightedGreen + weightedBlue
        self.textColor = labelTextColorFormula > Rgb.threshold ? .black : .white
    }
}
