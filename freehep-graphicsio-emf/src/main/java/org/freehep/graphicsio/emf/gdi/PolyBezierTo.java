// Copyright 2002, FreeHEP.
package org.freehep.graphicsio.emf.gdi;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import org.freehep.graphicsio.emf.EMFInputStream;
import org.freehep.graphicsio.emf.EMFOutputStream;
import org.freehep.graphicsio.emf.EMFTag;

/**
 * PolyBezierTo TAG.
 * 
 * @author Mark Donszelmann
 * @version $Id: freehep-graphicsio-emf/src/main/java/org/freehep/graphicsio/emf/gdi/PolyBezierTo.java f2f1115939ae 2006/12/07 07:50:41 duns $
 */
public class PolyBezierTo extends EMFTag {

    private Rectangle bounds;

    private int numberOfPoints;

    private Point[] points;

    public PolyBezierTo() {
        super(5, 1);
    }

    public PolyBezierTo(Rectangle bounds, int numberOfPoints, Point[] points) {
        this();
        this.bounds = bounds;
        this.numberOfPoints = numberOfPoints;
        this.points = points;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len)
            throws IOException {

        Rectangle r = emf.readRECTL();
        int n = emf.readDWORD();
        PolyBezierTo tag = new PolyBezierTo(r, n, emf.readPOINTL(n));
        return tag;
    }

    public void write(int tagID, EMFOutputStream emf) throws IOException {
        emf.writeRECTL(bounds);
        emf.writeDWORD(numberOfPoints);
        emf.writePOINTL(numberOfPoints, points);
    }

    public String toString() {
        return super.toString() + "\n" + "  bounds: " + bounds + "\n"
                + "  #points: " + numberOfPoints;
    }
}