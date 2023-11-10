import Foundation

@objc public class CustomJitsiMeet: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
