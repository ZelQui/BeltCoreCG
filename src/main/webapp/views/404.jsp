<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Error 404 - Página no encontrada</title>
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f8f9fa;
      color: #333;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      overflow: hidden;
      position: relative;
    }

    .error-page {
      text-align: center;
      background-color: white;
      border-radius: 20px;
      box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
      padding: 4rem;
      max-width: 90%;
      width: 600px;
      position: relative;
      z-index: 10;
      animation: fadeIn 0.8s ease-in-out;
    }

    .error-page h2 {
      font-size: 3.5rem;
      margin-bottom: 0.8rem;
      background: linear-gradient(45deg, #3a7bd5, #3a6073);
      -webkit-background-clip: text;
      background-clip: text;
      color: transparent;
      font-weight: 700;
    }

    .error-page p {
      font-size: 1.2rem;
      margin-bottom: 1.5rem;
      color: #666;
      line-height: 1.6;
    }

    .back-home {
      display: inline-block;
      background: linear-gradient(45deg, #3a7bd5, #3a6073);
      color: white;
      padding: 12px 25px;
      border-radius: 50px;
      text-decoration: none;
      font-weight: 600;
      margin-top: 1.5rem;
      transition: transform 0.3s, box-shadow 0.3s;
    }

    .back-home:hover {
      transform: translateY(-3px);
      box-shadow: 0 10px 20px rgba(58, 123, 213, 0.3);
    }

    .error-bg {
      position: absolute;
      width: 100%;
      height: 100%;
      top: 0;
      left: 0;
      z-index: 1;
      overflow: hidden;
    }

    .error-bg::before {
      content: '';
      position: absolute;
      top: -50%;
      left: -50%;
      width: 200%;
      height: 200%;
      background: radial-gradient(circle, rgba(240, 243, 247, 0.8) 0%, rgba(248, 249, 250, 1) 70%);
      animation: rotate 25s linear infinite;
      z-index: -1;
    }

    .error-icon {
      font-size: 8rem;
      opacity: 0.15;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      z-index: -1;
    }

    @keyframes rotate {
      from {
        transform: rotate(0deg);
      }
      to {
        transform: rotate(360deg);
      }
    }

    @keyframes fadeIn {
      from {
        opacity: 0;
        transform: translateY(20px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    @media (max-width: 768px) {
      .error-page {
        padding: 2rem;
        width: 90%;
      }

      .error-page h2 {
        font-size: 2.5rem;
      }

      .error-page p {
        font-size: 1rem;
        margin-bottom: 1rem;
      }
    }
  </style>
</head>
<body>
<div class="error-bg"></div>

<div class="error-page">
  <div class="error-icon">404</div>
  <h2>Error 404</h2>
  <p>No se ha encontrado la página solicitada.</p>
  <p>Por favor, verifique la ruta a la que desea acceder.</p>
  <button onclick="window.location.href='menu.jsp'" class="back-home">Volver al inicio</button>
</div>

</body>
</html>