package org.spring.streaming.ragollama.service;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PDFLoader {
  @Value("classpath:arch-guide-wip.pdf")
  Resource pdfResource;
  private VectorStore vectorStore;

  public PDFLoader(VectorStore vectorStore) {
    this.vectorStore = vectorStore;
  }

  @PostConstruct
  public void loadPDF() {
    TikaDocumentReader documentReader = new TikaDocumentReader(pdfResource);
    List<Document> documents = documentReader.get();
    vectorStore.add(documents);
  }
}
