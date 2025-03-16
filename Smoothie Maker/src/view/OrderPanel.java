package view;

import controller.GameController;
import model.Order;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderPanel extends JPanel {
    private GameController controller;
    private JList<Order> orderList;
    private DefaultListModel<Order> listModel;
    private Timer uiRefreshTimer;

    public OrderPanel(GameController controller) {
        this.controller = controller;
        initializeUI();
        setupRefreshTimer();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        listModel = new DefaultListModel<>();
        orderList = new JList<>(listModel);
        orderList.setCellRenderer(new OrderListRenderer());
        orderList.setBackground(new Color(240, 240, 240));

        JScrollPane scrollPane = new JScrollPane(orderList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Active Orders"));
        add(scrollPane, BorderLayout.CENTER);
        

    }

    public void updateOrders(List<Order> orders) {
        listModel.clear();
        orders.forEach(listModel::addElement);
    }

    private void setupRefreshTimer() {
        uiRefreshTimer = new Timer(1000, e -> updateOrders(controller.getActiveOrders()));
        uiRefreshTimer.start();
    }

    private class OrderListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Order order = (Order) value;
            
            label.setText(String.format("Order #%d - Time: %d seconds", 
                order.getOrderId(), order.getTimeRemaining()));
            label.setIconTextGap(10);
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            
            if(order.getTimeRemaining() < 10) {
                label.setForeground(Color.RED);
            } else {
                label.setForeground(Color.BLACK);
            }
            return label;
        }
    }
}