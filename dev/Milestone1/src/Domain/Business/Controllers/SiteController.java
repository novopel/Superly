package Domain.Business.Controllers;

import Domain.Business.Objects.Site.Address;
import Domain.Business.Objects.Site.Destination;
import Domain.Business.Objects.Site.Source;
import Domain.DAL.Controllers.TransportMudel.DestinationsDAO;
import Domain.DAL.Controllers.TransportMudel.SourcesDAO;
import Globals.Enums.ShippingAreas;

import java.util.HashMap;
//TODO not finished methods (GET) for each site
public class SiteController {
    private final SourcesDAO sourcesDataMapper = new SourcesDAO();
    private final DestinationsDAO destinationsDataMapper = new DestinationsDAO();
    public SiteController() {
        //TODO: In Milestone 3 connect the data to DB
        Source s1 = new Source(new Address(ShippingAreas.North, "Tiberia Shlomo Hamelech 136"), "Shalom", "050");
        Source s2 = new Source(new Address(ShippingAreas.South,"dimona Negev mount 19"),"eldad","05");
        Source s3 = new Source(new Address(ShippingAreas.Northeast,"Beer Sheva rager 20"),"yossi","123");
        sourcesDataMapper.save(s1);
        sourcesDataMapper.save(s2);
        sourcesDataMapper.save(s3);
        Destination d1 = new Destination(new Address(ShippingAreas.East,"Ashkelon"),"roni","4321");
        Destination d2 = new Destination(new Address(ShippingAreas.South,"eilat"),"yoav","2222");
        Destination d3 = new Destination(new Address(ShippingAreas.Northeast,"Tel Aviv"),"David","0123");
        destinationsDataMapper.save(d1);
        destinationsDataMapper.save(d2);
        destinationsDataMapper.save(d3);
    }

    public Source getSource(int id) throws Exception {
        Source src = sourcesDataMapper.get(id);
        if(src == null){
            throw new Exception("Source not found");
        }
        return src;
    }

    public Destination getDestination(int id) throws Exception {
        Destination dst = destinationsDataMapper.get(id);
        if(dst == null){
            throw new Exception("Source not found");
        }
        return dst;
    }

}
