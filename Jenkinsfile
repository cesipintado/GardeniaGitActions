// 🚀 Jenkinsfile - Pipeline de CI/CD para aplicación ecommerce adaptado para Gardenia
// 📦 Automatiza construcción, pruebas, análisis de calidad y despliegue Dockerizado

pipeline {
    agent any

    // 🛠️ Configuración de herramientas necesarias
    tools {
        maven 'MAVEN_HOME'
    }

    // 🔧 Variables globales del entorno
    environment {
        DOCKER_PROJECT_NAME = 'gardenia_project'
        APP_CONTAINER_NAME = 'gardenia_app'
        DB_CONTAINER_NAME = 'mysql-gardenia'
        DB_NAME = 'gardenia'
        DB_USER = 'root'
        DB_PASSWORD = '202100067'
        REPO_URL = 'https://github.com/JuanJoseHack/Gardenia-Remasterizado-.git'
    }

    stages {
        // 📥 Etapa 1: Clonación y verificación del proyecto
        stage('Clone') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    echo '🔄 === INICIO: CLONACIÓN DEL REPOSITORIO ==='
                    cleanWs()
                    git branch: 'master', url: "${REPO_URL}"

                    echo '📋 === VERIFICACIÓN DE ARCHIVOS SQL ==='
                    sh 'ls -la sql/'
                    sh '''
                        if [ -f "sql/init.sql" ]; then
                            echo "✅ Archivo init.sql encontrado correctamente"
                            echo "📄 Contenido inicial del archivo:"
                            head -n 5 sql/init.sql
                        else
                            echo "❌ ERROR: Archivo init.sql no encontrado"
                            exit 1
                        fi
                    '''
                    echo '✅ === FIN: CLONACIÓN Y VERIFICACIÓN COMPLETADA ==='
                }
            }
        }

        // 🏗️ Etapa 2: Construcción del proyecto
        stage('Build') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    echo '🔨 === INICIO: CONSTRUCCIÓN DEL PROYECTO ==='
                    sh 'mvn -DskipTests clean package'
                    echo '✅ === FIN: CONSTRUCCIÓN COMPLETADA ==='
                }
            }
        }

        // 🧪 Etapa 3: Ejecución de pruebas unitarias
        stage('Test') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    echo '🧪 === INICIO: EJECUCIÓN DE PRUEBAS UNITARIAS ==='
                    sh 'mvn test -DskipTests'
                    echo '✅ === FIN: PRUEBAS COMPLETADAS ==='
                }
            }
        }

        // 📊 Etapa 4: Análisis de calidad con SonarQube
        stage('Sonar Analysis') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    echo '📊 === INICIO: ANÁLISIS DE CALIDAD ==='
                    withSonarQubeEnv('sonarqube') {
                        sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Pcoverage'
                    }
                    echo '✅ === FIN: ANÁLISIS DE CALIDAD COMPLETADO ==='
                }
            }
        }

        // 🎯 Etapa 5: Verificación de calidad
        stage('Quality Gate') {
            steps {
                timeout(time: 30, unit: 'MINUTES') {
                    echo '🎯 === VERIFICACIÓN DE ESTÁNDARES DE CALIDAD ==='
                    waitForQualityGate abortPipeline: true
                    echo '✅ === FIN: VERIFICACIÓN DE CALIDAD COMPLETADA ==='
                }
            }
        }

        // 🚀 Etapa 6: Despliegue con Docker Compose
        stage('Deploy Application') {
            steps {
                echo '🚀 === INICIO: PROCESO DE DESPLIEGUE ==='
                script {
                    // 🧹 Limpieza previa del entorno Docker
                    echo '1️⃣ Limpiando despliegue anterior...'
                    try {
                        sh "docker-compose down -v --remove-orphans"
                    } catch (Exception e) {
                        echo "⚠️ Advertencia al limpiar: ${e.getMessage()}"
                    }

                    // 🏗️ Construcción y despliegue
                    echo '2️⃣ Construyendo y levantando servicios...'
                    sh 'docker-compose build'
                    sh 'docker-compose up -d'

                    // 💾 Espera inicialización y verificación de DB
                    echo '3️⃣ Esperando inicialización de la base de datos...'
                    sleep(40)
                    sh "docker exec ${DB_CONTAINER_NAME} mysql -u${DB_USER} -p${DB_PASSWORD} ${DB_NAME} < sql/init.sql"

                    // 🔍 Verificación de base de datos
                    echo '4️⃣ Verificando estructura de la base de datos...'
                    sh "docker exec ${DB_CONTAINER_NAME} mysql -u${DB_USER} -p${DB_PASSWORD} -e 'USE ${DB_NAME}; SHOW TABLES;'"

                    // ⏳ Espera de arranque de app y logs
                    echo '5️⃣ Esperando arranque de la aplicación...'
                    sleep(30)
                    echo '6️⃣ Logs recientes de la aplicación:'
                    sh "docker logs --tail 200 ${APP_CONTAINER_NAME}"
                }
                echo '✅ === FIN: DESPLIEGUE COMPLETADO ==='
            }
        }
    }

    // 📝 Acciones post-pipeline
    post {
        always {
            echo '🏁 === FINALIZACIÓN DEL PIPELINE ==='
        }
        success {
            echo '🎉 ✓ Pipeline completado exitosamente'
        }
        failure {
            echo '💥 ✗ Pipeline falló'
        }
    }
}
