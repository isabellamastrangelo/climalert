# Climalert 

Climalert es un servicio autónomo desarrollado en **Spring Boot** sin interfaz gráfica. Su responsabilidad principal es monitorear constantemente el clima de una ubicación fija y enviar alertas automáticas por correo electrónico cuando se detectan condiciones meteorológicas peligrosas.

Este proyecto fue desarrollado como práctica para la cátedra de **Diseño de Sistemas de Información** (UTN FRBA) el 1° Cuatrimestre de 2026.

---

## Funcionalidades Principales

El sistema realiza las siguientes tareas:

1. **Integración Externa (Cada 5 minutos):**
   Se conecta vía REST a la API de **WeatherAPI** (endpoint `/current.json`) para obtener los datos climáticos actuales de la ciudad configurada (ej. CABA). Estos datos se almacenan en una estructura de memoria temporal para su registro histórico local.

2. **Procesamiento de Alertas (Cada 1 minuto):**
   Un evaluador analiza la última medición disponible. Para esta primera iteración, se dispara una alerta de condición crítica si se cumplen simultáneamente estas condiciones:
   * Temperatura mayor a **35°C**.
   * Humedad superior al **60%**.

3. **Notificación por Correo Electrónico:**
   Utilizando un patrón basado en Eventos (*Observer*), el sistema reacciona a las condiciones críticas enviando un correo electrónico con el detalle completo del clima. Los destinatarios configurados por defecto son:
   * `admin@clima.com` 
   * `emergencias@clima.com` 
   * `meteorologia@clima.com` 

---

## Tecnologías Utilizadas

* **Java** (JDK 17+)
* **Spring Boot** 
* **Maven** 

