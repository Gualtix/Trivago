#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    SERVER = "";
    APPLICATION = "";
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_btnTicketComprar_clicked()
{
    int codigo = ui->cmbTicketMonto->currentIndex();

    switch (codigo) {
    case TRES:
        codigo = 3;
        break;
    case DIEZ:
        codigo = 10;
        break;
    case CINCUENTA:
        codigo = 50;
        break;
    case CIEN:
        codigo = 100;
        break;
    default:
        return;
    }
}

void MainWindow::on_btnDevolver_clicked()
{
    int codigo = ui->edtDevolverCodigo->text().toInt();
}

QString MainWindow::enviarPeticion(QString string)
{
    QString json = tr("{\"kiosko\":\"K052336\",\"monto\":%1}").arg(codigo);

    QEventLoop loop;

    QNetworkAccessManager accessManager;
    QObject::connect(&accessManager, SIGNAL(finished(QNetworkReply*)),
                     &loop,
                     SLOT(quit())
                     );

    QNetworkRequest request(QUrl(url + QString("/tickets")));
    request.setRawHeader("Content-Type", "application/json");

    QNetworkReply *reply = accessManager.post(request, json.toUtf8());
    loop.exec();

    if (reply->error() == QNetworkReply::NoError)
    {
        QByteArray response = reply->readAll();
        QJsonDocument jsd = QJsonDocument::fromJson(response);
        QJsonObject jso = jsd.object();

        ui->edtTicketCodigo->setText(QString::number(jso["codigo"].toString()));
        ui->edtTicketVerificacion->setText(jso["verificacion"].toString());

        qDebug() << "Success\n" << response;
        delete reply;
    }
    else
    {
        qDebug() << "Failure " << reply->errorString();
        delete reply;
    }
}
