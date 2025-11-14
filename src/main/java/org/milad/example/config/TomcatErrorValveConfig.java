/*

import org.apache.catalina.Context;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ErrorReportValve;
import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.PrintWriter;

*/
/**
 * Full configuration that replaces Tomcat’s ErrorReportValve and
 * registers a custom 400 error page.
 *//*

@Configuration
public class TomcatErrorValveConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customTomcatErrorValve() {
        return factory -> factory.addContextCustomizers((Context context) -> {
            // --- 1. Register custom 400 error page path ---
            ErrorPage errorPage400 = new ErrorPage();
            errorPage400.setErrorCode(400);
            errorPage400.setLocation("/custom-400.html");
            context.addErrorPage(errorPage400);
            // Add our custom ErrorReportValve
            context.getParent().getPipeline().addValve(new CustomErrorReportValve());
        });
    }

    */
/**
     * Custom ErrorReportValve that handles all errors (including malformed URLs).
     *//*

    static class CustomErrorReportValve extends ErrorReportValve {

        public CustomErrorReportValve() {
            super();
            // Prevent Tomcat from adding its own HTML
            setShowReport(false);
            setShowServerInfo(false);
        }

        @Override
        protected void report(Request request, Response response, Throwable throwable) {
            try {
                if (response.getStatus() == 400) {
                    // --- Our own response for 400 Bad Request ---
                    response.setContentType("text/html;charset=UTF-8");
                    response.setStatus(400);
                    PrintWriter writer = response.getWriter();
                    writer.println("""
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <title>Bad Request</title>
                                <style>
                                    body { font-family: Arial, sans-serif; background: #f9f9f9; color: #333; text-align:center; padding-top: 80px;}
                                    h1 { color: #e74c3c; font-size: 42px; }
                                    p { font-size: 18px; color: #555; }
                                </style>
                            </head>
                            <body>
                                <h1>400 - Bad Request</h1>
                            </body>
                            </html>
                            """);
                    writer.flush();
                    return;
                }
                // fallback for other errors
                super.report(request, response, throwable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
*/
