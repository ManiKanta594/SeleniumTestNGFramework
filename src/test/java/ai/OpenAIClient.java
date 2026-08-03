package ai;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * ==========================================================
 * Class Name : OpenAIClient
 * Package    : ai
 * Author     : Manikanta
 * ==========================================================
 *
 * Purpose
 * ----------------------------------------------------------
 * This class is responsible for communicating with
 * OpenAI ChatGPT API.
 *
 * Responsibilities
 * ----------------------------------------------------------
 * 1. Connect to OpenAI
 * 2. Send Prompt
 * 3. Receive AI Response
 * 4. Return AI Output
 *
 * Real Time Usage
 * ----------------------------------------------------------
 * Framework
 *      |
 *      V
 * Failure Analyzer
 *      |
 *      V
 * OpenAIClient
 *      |
 *      V
 * ChatGPT API
 *      |
 *      V
 * AI Response
 *
 * ==========================================================
 */

public class OpenAIClient {

    // ==========================================================
    // OpenAI API URL
    // ==========================================================
    private static final String API_URL =
            "https://api.openai.com/v1/chat/completions";

    // ==========================================================
    // Store your API Key here
    //
    // Better Approach:
    // Read this from config.properties later.
    // ==========================================================
   private static final String API_KEY =
            "YOUR_OPENAI_API_KEY_HERE";
    // ==========================================================
    // JSON Media Type
    // ==========================================================
    private static final MediaType JSON =
            MediaType.parse("application/json");

    // ==========================================================
    // Reusable HTTP Client
    //
    // Interview Question
    // ------------------
    // Why static?
    //
    // Because OkHttpClient supports connection pooling.
    // Reusing the client improves performance.
    // ==========================================================
    private static final OkHttpClient client =
            new OkHttpClient();

    /**
     * ==========================================================
     * Method Name : askAI()
     * ==========================================================
     *
     * Purpose
     * ----------------------------------------------------------
     * Sends prompt to OpenAI
     *
     * Returns
     * ----------------------------------------------------------
     * AI Response
     *
     * ==========================================================
     */
    public static String askAI(String prompt)
            throws IOException {

        // ======================================================
        // Build JSON Request
        //
        // Model Used
        // GPT-5.5
        // ======================================================
        String json =
                "{"
              + "\"model\":\"gpt-5.5\","
              + "\"messages\":["
              + "{"
              + "\"role\":\"user\","
              + "\"content\":\""
              + prompt.replace("\"", "\\\"")
              + "\""
              + "}"
              + "]"
              + "}";

        // ======================================================
        // Create Request Body
        // ======================================================
        RequestBody body =
                RequestBody.create(json, JSON);

        // ======================================================
        // Build HTTP Request
        // ======================================================
        
        
        Request request =
                new Request.Builder()

                .url(API_URL)

                .addHeader(
                        "Authorization",
                        "Bearer " + API_KEY)

                .addHeader(
                        "Content-Type",
                        "application/json")

                .post(body)

                .build();

        // ======================================================
        // Send Request
        // ======================================================
        Response response =
                client.newCall(request).execute();

        // ======================================================
        // Return AI Response
        // ======================================================
        return response.body().string();

    }

}