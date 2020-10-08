package placefinder;

import model.PlaceLocation;

import java.util.HashSet;
import java.util.Set;

public class TestPlacesFactory {

    public static Set<PlaceLocation> getPlaceLocations() {
        Set<PlaceLocation> placeLocations = new HashSet<>();
        addLocations(placeLocations);
        return placeLocations;
    }

    private static void addLocations(Set<PlaceLocation> locations) {
        locations.add(new PlaceLocation("GRAL. MANUEL BELGRANO", -34.61278, -58.371669999999995));
        locations.add(new PlaceLocation("JORGE ALEJANDRO NEWBERY", -34.58723, -58.45585));
        locations.add(new PlaceLocation("MUSEO PARLAMENTARIO \"SENADOR DOMINGO FAUSTINO SARMIENTO\"", -34.6102755, -58.39058370000001));
        locations.add(new PlaceLocation("BIBLIOTECA MUSEO SIMIK", -34.5844008040291, -58.4535734876737));
        locations.add(new PlaceLocation("EDIFICIO KAVANAGH", -34.59538, -58.3744));
        locations.add(new PlaceLocation("LA INTERNACIONAL ARGENTINA", -34.59837322, -58.44427555));
        locations.add(new PlaceLocation("EL ALEPH", -34.601766999999995, -58.433389500000004));
        locations.add(new PlaceLocation("CINEMARK CABALLITO", -34.6163592828158, -58.428870659641504));
        locations.add(new PlaceLocation("LA MINGA CLUB CULTURAL", -34.6118021, -58.417019599999996));
        locations.add(new PlaceLocation("CENTRO CULTURAL ALFONSINA STORNI", -34.6003247, -58.4116159));
        locations.add(new PlaceLocation("CASA DE LA MONEDA", -34.615140000000004, -58.37175));
        locations.add(new PlaceLocation("HOSPITAL DEL REY", -34.616170000000004, -58.39774));
        locations.add(new PlaceLocation("TANGO ESCUELA CARLOS COPELLO", -34.6028416887619, -58.409853177283004));
        locations.add(new PlaceLocation("A.M.I.A.", -34.60194, -58.399719999999995));
        locations.add(new PlaceLocation("LA PERLA BAR", -34.610396597663105, -58.40583961694821));
        locations.add(new PlaceLocation("HOTEL BAUEN", -34.6050732, -58.3923427));
        locations.add(new PlaceLocation("CASA DE GOBIERNO (CASA ROSADA)", -34.60828, -58.37084));
        locations.add(new PlaceLocation("PALACIO BAROLO", -34.6094, -58.38576));
        locations.add(new PlaceLocation("SERGIO SOUZA", -34.608572220976896, -58.3830610358858));
        locations.add(new PlaceLocation("CAFE TORTORI", -34.6088246235812, -58.3781261295908));
        locations.add(new PlaceLocation("CABILDO DE LA CIUDAD DE BUENOS AIRES", -34.60863, -58.373459999999994));
    }
}

/*
placeLocations.add(new PlaceLocation("GRAL. MANUEL BELGRANO", -34.61278, -58.371669999999995));
        placeLocations.add(new PlaceLocation("GRAL. MANUEL BELGRANO", -34.61278, -58.371669999999995));
        placeLocations.add(new PlaceLocation("JORGE ALEJANDRO NEWBERY", -34.58723, -58.45585));
        placeLocations.add(new PlaceLocation("MUSEO PARLAMENTARIO \"SENADOR DOMINGO FAUSTINO SARMIENTO\"", -34.6102755, -58.39058370000001));
        placeLocations.add(new PlaceLocation("BIBLIOTECA MUSEO SIMIK", -34.5844008040291, -58.4535734876737));
        placeLocations.add(new PlaceLocation("EDIFICIO KAVANAGH", -34.59538, -58.3744));
        placeLocations.add(new PlaceLocation("LA INTERNACIONAL ARGENTINA", -34.59837322, -58.44427555));
        placeLocations.add(new PlaceLocation("EL ALEPH", -34.601766999999995, -58.433389500000004));
        placeLocations.add(new PlaceLocation("CINEMARK CABALLITO", -34.6163592828158, -58.428870659641504));
        placeLocations.add(new PlaceLocation("LA MINGA CLUB CULTURAL", -34.6118021, -58.417019599999996));
        placeLocations.add(new PlaceLocation("CENTRO CULTURAL ALFONSINA STORNI", -34.6003247, -58.4116159));
        placeLocations.add(new PlaceLocation("CASA DE LA MONEDA", -34.615140000000004, -58.37175));
        placeLocations.add(new PlaceLocation("HOSPITAL DEL REY", -34.616170000000004, -58.39774));
        placeLocations.add(new PlaceLocation("TANGO ESCUELA CARLOS COPELLO", -34.6028416887619, -58.409853177283004));
        placeLocations.add(new PlaceLocation("A.M.I.A.", -34.60194, -58.399719999999995));
        placeLocations.add(new PlaceLocation("LA PERLA BAR", -34.610396597663105, -58.40583961694821));
        placeLocations.add(new PlaceLocation("HOTEL BAUEN", -34.6050732, -58.3923427));
        placeLocations.add(new PlaceLocation("CASA DE GOBIERNO (CASA ROSADA)", -34.60828, -58.37084));
        placeLocations.add(new PlaceLocation("PALACIO BAROLO", -34.6094, -58.38576));
        placeLocations.add(new PlaceLocation("SERGIO SOUZA", -34.608572220976896, -58.3830610358858));
        placeLocations.add(new PlaceLocation("CAFE TORTORI", -34.6088246235812, -58.3781261295908));
        placeLocations.add(new PlaceLocation("CABILDO DE LA CIUDAD DE BUENOS AIRES", -34.60863, -58.373459999999994));
 */
