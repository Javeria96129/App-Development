import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.VideoCapture;

public class ObjectDetection {
    static { System.loadlibrary(Core.NATIVE_LIBRARY_NAME; }
    
    public static void main(String[] args) {
        //Load YOLOv3 model
        Net net = Dnn.readNet("yolov3.weights", "yolov3.cfg");
        
        //open video capture
        video capture = new VideoCapture(0);
        
        while (true) {
            //Read frame = new Mat();
            capture.read(frame);
            
            //Detect objects
            Mat blob = Dnn.blobFromImage(frame, 1/255.0,new Size(416, 416), new Scalar(0), true, false);
            net.setInput(blob);
            Mat outs = net.forward();
            
            //Process detections
            for (int = 0;< outs.row(); i++) {
            float confidence = (float) outs.get(row:i, col:4) [0];
            if (confidence > 0.5) {
                int classId = (int) outs.get(row:i, col:5)[0];
                String className = classes[classId];
                
                //Draw bounding box
                double[] coords = outs.get(row:i, col:0);
                int x = (int) (coords[0] * frame.colos());
                int y = (int) (coords[1] * frame.rows());
                int w = (int) (coords[2] * frame.cols());
                int h = (int) (coords[3] * frame.rows());
                Improc.rectangle(frame, new POint(x, y), new Point(x + w, y + h), new Scalar(v0:0, v1:255, v2:0))
                Improc.putText(frame, className,new Point(x, y - 10), Improc.FONT_HERSHEY_SIMPLEX, 0.5, newScalar() )
            }
            
            //Display output
            Improc.imshow("Object Deletion", frame);
            
            //Exit on ESC key press
            if (Imgproc.waitkey(27) == 27) {
                break;
            }
            //Release resouorce
            capture.release();
            Improc.destroyAllWndow();
        }
    }
    