package com.apollo.pharmacy.ocr.utility;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.InputStream;

public class ImageManager {
    private String websiteUrl = "";

    public static final String storageConnectionString = "DefaultEndpointsProtocol=https;"
            //public static final String storageConnectionString = "https://aptestingweb.file.core.windows.net/testing"
            + "AccountName=pharmtest;"
            + "AccountKey=dhJpbROM1e6MzzjAHPXyP52+w1U+cN2DQKnqwc77Uwp6LkIH/9k2hHktS3zpfJPCEQdL2jcqYdANTdEox+Fiww==";

    private static CloudBlobContainer getContainer(String uploadImageUrl, String uploadImageKey) throws Exception {
        // Retrieve storage account from connection-string.
        CloudStorageAccount storageAccount = CloudStorageAccount
                .parse(storageConnectionString);

        // Create the blob client.
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

        // Get a reference to a container.
        // The container name must be lower case
        CloudBlobContainer container = blobClient.getContainerReference("cms");

        return container;
    }

    public static String UploadImage(InputStream image, int imageLength, String transactionGeneratedId, String uploadImageUrl, String uploadImageKey) throws Exception {
        CloudBlobContainer container = getContainer(uploadImageUrl, uploadImageKey);

        container.createIfNotExists();

        String imageName = "ApolloKiosk_" + transactionGeneratedId + ".jpg";

        CloudBlockBlob imageBlob = container.getBlockBlobReference(imageName);
        imageBlob.upload(image, imageLength);

        return imageBlob.getStorageUri().getPrimaryUri().toString();
    }
}
