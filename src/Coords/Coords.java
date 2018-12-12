package Coords;

import Geom.Point3D;

public class Coords implements coords_converter {

	final static int earth_rad = 6371000;
	final static double norm_lan = 0.847091174;
    
	/*
	 * This function computes a new point which is the gps point transformed by a 3D vector (in meters).
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {

		double comp = local_vector_in_meter.x() / earth_rad;
		double lat = gps.x() + (comp * 180 / Math.PI);
		double comp2 = (local_vector_in_meter.y() + (comp * 180 / Math.PI)) / earth_rad;
		double lon = gps.y() + (comp2 * 180 / Math.PI);
		double alt = gps.z() + local_vector_in_meter.z();
		return new Point3D(lat, lon, alt);
	}

	/**
	 * This function computes the 3D distance (in meters) between the two gps like points.
	 * I used this website to get information for this function:
	 * https://stackoverflow.com/questions/5557706/calculating-distance-using-latitude-longitude-coordinates-in-kilometers-with-jav
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {

		double theDistance = (Math.sin(Math.toRadians(gps0.x())) *
				Math.sin(Math.toRadians(gps1.x()))) +
				(Math.cos(Math.toRadians(gps0.x())) * Math.cos(Math.toRadians(gps1.x())) *
						Math.cos(Math.toRadians(gps0.y() - gps1.y() )));
		return new Double(((Math.toDegrees(Math.acos(theDistance))) * 69.09)* 1.60934) * 1000;
	}

	/*
	 * This function computes the 3D vector (in meters) between two gps like points.
	 */
	public Point3D vector3D(Point3D gps0, Point3D gps1) {

		double radX = (gps1.x() - gps0.x()) * Math.PI / 180;
		double radY = (gps1.y() - gps0.y()) * Math.PI / 180;
		return new Point3D(Math.sin(radX) * earth_rad, Math.sin(radY) * earth_rad * norm_lan, gps1.z() - gps0.z());
	}

	/** computes the polar representation of the 3D vector be gps0-->gps1.
	 * I used this website for this function:
	 * https://www.omnicalculator.com/other/azimuth#how-to-calculate-the-azimuth-from-latitude-and-longitude.
	 */
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {

		double x = Math.sin(Math.toRadians(gps1.y() - gps0.y()) * Math.cos(Math.toRadians(gps1.x())));
		double y = Math.cos(Math.toRadians(gps0.x()))
				   * Math.sin(Math.toRadians(gps1.x())) - Math.sin(Math.toRadians(gps0.x()))
				   * Math.cos(Math.toRadians(gps1.x())) * Math.cos(Math.toRadians(gps1.y() - gps0.y()));
		double azi = Math.atan2(x, y);
		azi = Math.toDegrees(azi) + 360;
		double dis = distance3d(gps0, gps1);
		double el = Math.toDegrees(Math.asin((gps1.z() - gps0.z()) / dis));
		double[] ans = {azi, el, dis};
		return ans;
	}

	/**
	 * This function returns true if this point is a valid coordinate.
	 * lat : [-180, +180].
	 * lon : [-90, +90].
	 * alt : [-450, +inf].
	 * else - return false.
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {

		if(p.x() >= -180 && p.x() <= 180 && p.y() >= -90 && p.y() <= 90 && p.z() >= -450) {
			return true;
		}
		return false;
	}

}