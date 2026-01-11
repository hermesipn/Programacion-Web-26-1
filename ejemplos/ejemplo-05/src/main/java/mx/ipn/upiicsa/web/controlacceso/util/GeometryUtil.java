package mx.ipn.upiicsa.web.controlacceso.util;

import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;

public class GeometryUtil {

    public static Point wkbToPoint(byte[] wkb) {
        try {
            if (wkb == null) return null;
            return (Point) new WKBReader().read(wkb);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] pointToWkb(Point point) {
        if (point == null) return null;
        return new WKBWriter().write(point);
    }
}