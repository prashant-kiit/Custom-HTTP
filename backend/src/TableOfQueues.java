package backend.src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import library.Request;

public class TableOfQueues {
    private Map<String, Queue<Request>> queueTable;

    public TableOfQueues() {
        this.queueTable = new HashMap<String, Queue<Request>>();
    }

    public void insert(Request request) {
        String method = request.getMethod();
        String path = request.getPath();
        Queue<Request> queueRequests = queueTable.get(method + ":" + path);
        if (queueRequests == null) {
            queueRequests = new LinkedList<>();
        }
        queueRequests.add(request);
        queueTable.put(method + ":" + path, queueRequests);
    }

    public Request read(String methodAndPath) {
        Queue<Request> queueRequests = queueTable.get(methodAndPath);
        return queueRequests.poll();
    }
}
