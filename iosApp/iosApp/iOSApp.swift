import SwiftUI
import shared

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
            let countryUseCase = LoadCountryUseCase.companion.create()
            let countryViewModel = CountryViewModel(countryUseCase: countryUseCase)
			ContentView(viewModel: countryViewModel)
		}
	}
}
