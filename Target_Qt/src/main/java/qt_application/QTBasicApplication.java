
package main.java.qt_application;

import io.qt.core.Qt;
import io.qt.gui.QCloseEvent;
import io.qt.widgets.QApplication;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QLabel;
import io.qt.widgets.QWidget;


public class QTBasicApplication extends QMainWindow {

    public QTBasicApplication()
    {

        QWidget window = new QWidget();
        window.setWindowTitle("3x3 Grid of Labels");
        QGridLayout gridLayout = new QGridLayout(window);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                QLabel label = new QLabel("Label " + (i * 3 + j + 1));
                label.setAlignment(Qt.AlignmentFlag.AlignCenter);
                gridLayout.addWidget(label, i, j);
            }
        }
        window.show();

    }

    @Override
    public void closeEvent(QCloseEvent event)
    {
        event.accept();
    }

    public static void main(String[] args) {
        QApplication.initialize(args);
        new QTBasicApplication();
        QApplication.exec();
    }

}