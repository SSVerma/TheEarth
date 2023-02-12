import SwiftUI
import shared

struct ContentView: View {
    @StateObject var viewModel: CountryViewModel

	var body: some View {
        let state = viewModel.countryState
        
        switch state {
        case .loading:
            ProgressView()
        case .failed:
            Text("Something went wrong")
        case .loaded(let countries):
            List {
                ForEach(countries, id: \.self) { country in
                    CountryItem(country: country)
                }
            }
        }
	}
}

struct CountryItem: View {
    let country: Country_

    var body: some View {
        VStack {
            HStack {
                Text(country.flagEmoji)
                Text(country.name)
                    .font(.title)
                Spacer()
            }
            Text(country.native)
                .frame(maxWidth: .infinity, alignment: .leading)
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}
