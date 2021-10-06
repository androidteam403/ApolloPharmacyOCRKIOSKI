package com.apollo.pharmacy.ocr.utility;

import android.content.Context;

import com.apollo.pharmacy.ocr.R;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageUri;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.security.SecureRandom;
import java.util.UUID;

public class ImageManager {
    private String websiteUrl = "";

    public static final String storageConnectionString = "DefaultEndpointsProtocol=https;"
            //public static final String storageConnectionString = "https://aptestingweb.file.core.windows.net/testing"
            + "AccountName=aptestingweb;"
            + "AccountKey=Zj7CmXnxWtfpEYYE0F2Qhugczl4rnAZCs/mqMMaVom5H0d31ji/1+/7xSBAXu6sJvcE6YmhvPgoRyAN+uVcXkg==";

    private static CloudBlobContainer getContainer(String uploadImageUrl, String uploadImageKey) throws Exception {
        // Retrieve storage account from connection-string.
        CloudStorageAccount storageAccount = CloudStorageAccount
                .parse(storageConnectionString);

        // Create the blob client.
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

        // Get a reference to a container.
        // The container name must be lower case
        CloudBlobContainer container = blobClient.getContainerReference("images");

        return container;
    }

    public static String UploadImage(InputStream image, int imageLength, String transactionGeneratedId, String uploadImageUrl, String uploadImageKey) throws Exception {
        CloudBlobContainer container = getContainer(uploadImageUrl, uploadImageKey);

        container.createIfNotExists();

        String imageName = "ApolloKiosk_" + transactionGeneratedId;

        CloudBlockBlob imageBlob = container.getBlockBlobReference(imageName);
        imageBlob.upload(image, imageLength);

        return imageBlob.getStorageUri().getPrimaryUri().toString();
    }
}
