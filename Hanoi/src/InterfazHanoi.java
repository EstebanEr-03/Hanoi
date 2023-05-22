import javax.swing.*;
//import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InterfazHanoi extends JFrame {
    private JScrollBar scrollBar1;
    private JPanel panelPrincipal;
    private JTabbedPane tabbedPane1;
    private JTable tableTorreA;
    private JTable tableTorreB;
    private JTable tableTorreC;
    private JButton ButtonA_B;
    private JButton ButtonA_C;
    private JButton ButtonB_A;
    private JButton ButtonB_C;
    private JButton ButtonC_A;
    private JButton ButtonC_B;
    private JComboBox comboBoxNumDisc;
    private JTextField textFieldMinMov;
    private JTextField textFieldNumMov;
    private JButton inciarButton;
    private JButton reiniciarButton;
    private JButton resolverButton;

    int conNumMov=0;
    Pila pilaTorreA;
    Pila pilaTorreB;
    Pila pilaTorreC;

    //DefaultTableModel ModeloTablaA,ModeloTablaB,ModeloTablaC;
    DefaultTableModel ModeloTablaA=new DefaultTableModel(0,1);
    DefaultTableModel ModeloTablaB=new DefaultTableModel(0,1);
    DefaultTableModel ModeloTablaC=new DefaultTableModel(0,1);

    int objetivo=0;
    double numMinMov=0;
    boolean stop=false;
    boolean Stop=false;
    //DefaultTableCellRenderer renderA,renderB,renderC;


public InterfazHanoi() {

    tableTorreA.setModel(ModeloTablaA);
    tableTorreB.setModel(ModeloTablaB);
    tableTorreC.setModel(ModeloTablaC);

    add(panelPrincipal);
    setSize(900,900);
    setLocationRelativeTo(null);

    setTitle("Torres de Hanoi");

    ModeloTablaA=(DefaultTableModel) tableTorreA.getModel();
    ModeloTablaA.setRowCount(10);

    ModeloTablaB=(DefaultTableModel) tableTorreB.getModel();
    ModeloTablaB.setRowCount(10);

    ModeloTablaC=(DefaultTableModel) tableTorreC.getModel();
    ModeloTablaC.setRowCount(10);

    DefaultTableCellRenderer renderA=new DefaultTableCellRenderer();
    renderA.setHorizontalAlignment(SwingConstants.CENTER);
    tableTorreA.getColumnModel().getColumn(0).setCellRenderer(renderA);

    DefaultTableCellRenderer renderB=new DefaultTableCellRenderer();
    renderB.setHorizontalAlignment(SwingConstants.CENTER);
    tableTorreB.getColumnModel().getColumn(0).setCellRenderer(renderB);

    DefaultTableCellRenderer renderC=new DefaultTableCellRenderer();
    renderC.setHorizontalAlignment(SwingConstants.CENTER);
    tableTorreC.getColumnModel().getColumn(0).setCellRenderer(renderC);
    inciarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            conNumMov=0;
            iniciar();
        }
    });
    reiniciarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            reiniciar();
        }
    });
    ButtonA_B.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moverDeA_B();
        }
    });
    ButtonA_C.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moverDeA_C();
        }
    });
    ButtonB_A.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moverDeB_A();
        }
    });
    ButtonB_C.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moverDeB_C();
        }
    });
    ButtonC_A.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moverDeC_A();
        }
    });
    ButtonC_B.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moverDeC_B();
        }
    });

    resolverButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!textFieldMinMov.getText().equals("") && pilaTorreC.getContNodo()!=objetivo){
                reiniciar();
                Stop=false;
                resolverHanoiRecur(objetivo,pilaTorreA,pilaTorreB,pilaTorreC);
            }
        }
    });

}
private void limpiar(){
    conNumMov=0;
    numMinMov=0;

    comboBoxNumDisc.setSelectedItem(String.valueOf(objetivo));
}
private void presentarCantMov(){
    conNumMov++;
    textFieldNumMov.setText(String.valueOf(conNumMov));
}
private void reiniciar(){
    try {
        if (!textFieldMinMov.getText().equals(" ")){
            comboBoxNumDisc.setSelectedItem(String.valueOf(objetivo));
            limpiar();
            iniciar();
        }
    }catch (Exception E){
        System.out.println("Error: "+E.getMessage());
    }
}

private void iniciar(){
    try {

        pilaTorreA=new Pila();
        pilaTorreB=new Pila();
        pilaTorreC=new Pila();

        objetivo=Integer.parseInt(comboBoxNumDisc.getSelectedItem().toString());

        numMinMov=Math.pow(2,objetivo)-1;
        textFieldNumMov.setText(String.valueOf(conNumMov));
        textFieldMinMov.setText(String.valueOf(String.format("%.0f",numMinMov)));


        for (int x=objetivo; x>=1;x--){
            Nodo plataforma=new Nodo();
            String disco=" ";
            for(int y=x;y>0;y--){
                disco+="#";
            }
            plataforma.setDato(disco);
            plataforma.setDato(disco);
            pilaTorreA.push(plataforma);
        }
        presentarTorreA();
        presentarTorreB();
        presentarTorreC();
    }catch (Exception E){
        System.out.println("Error: "+E.getMessage());
    }

}

private void presentarTorreA(){
    ((DefaultTableModel)tableTorreA.getModel()).setRowCount(0);

    ModeloTablaA.setRowCount(10);
    Nodo k;
    int rowDisco=(10-pilaTorreA.getContNodo());

    if (pilaTorreA.getContNodo()>0){
        for (k= pilaTorreA.getCabeza();k.getAbajo()!=null;k=k.getAbajo()){
            String[] vectorNormal={k.getDato()};
            ModeloTablaA.insertRow(rowDisco,vectorNormal);
            rowDisco++;
        }

        if (k.getAbajo()==null){
            String[] vectorNormal={k.getDato()};
            ModeloTablaA.insertRow(rowDisco,vectorNormal);
        }
    }
    tableTorreA.setModel(ModeloTablaA);
    ModeloTablaA.setRowCount(10);

}

private void presentarTorreB(){
    ((DefaultTableModel)tableTorreB.getModel()).setRowCount(0);

    ModeloTablaB.setRowCount(10);
    Nodo k;
    int rowDisco=(10-pilaTorreB.getContNodo());

    if (pilaTorreB.getContNodo()>0){
        for (k= pilaTorreB.getCabeza();k.getAbajo()!=null;k=k.getAbajo()){
            String[] vectorNormal={k.getDato()};
            ModeloTablaB.insertRow(rowDisco,vectorNormal);
            rowDisco++;
        }

        if (k.getAbajo()==null){
            String[] vectorNormal={k.getDato()};
            ModeloTablaB.insertRow(rowDisco,vectorNormal);
        }
    }
    tableTorreB.setModel(ModeloTablaB);
    ModeloTablaB.setRowCount(10);

}

private void presentarTorreC(){
    ((DefaultTableModel)tableTorreC.getModel()).setRowCount(0);

    ModeloTablaC.setRowCount(10);
    Nodo k;
    int rowDisco=(10-pilaTorreC.getContNodo());

    if (pilaTorreC.getContNodo()>0){
        for (k= pilaTorreC.getCabeza();k.getAbajo()!=null;k=k.getAbajo()){
            String[] vectorNormal={k.getDato()};
            ModeloTablaC.insertRow(rowDisco,vectorNormal);
            rowDisco++;
        }

        if (k.getAbajo()==null){
            String[] vectorNormal={k.getDato()};
            ModeloTablaC.insertRow(rowDisco,vectorNormal);
        }
    }
    tableTorreC.setModel(ModeloTablaC);
    ModeloTablaC.setRowCount(10);

}
private void moverDeA_B(){
    try {
        if (pilaTorreA.getContNodo()>0){
            Nodo plataforma=new Nodo();
            plataforma.setDato(pilaTorreA.peek());

            if (pilaTorreB.getContNodo()>0){

                if (plataforma.getDato().compareTo(pilaTorreB.peek())>0){
                    return;
                }
            }
            pilaTorreA.pop();
            pilaTorreB.push(plataforma);

            presentarTorreA();
            presentarTorreB();
            presentarCantMov();
        }

    }catch (Exception E){
        System.out.println("Error: "+E.getMessage());
    }
}
private void moverDeA_C(){
    try {
        if (pilaTorreA.getContNodo()>0){
            Nodo plataforma=new Nodo();
            plataforma.setDato(pilaTorreA.peek());

            if (pilaTorreC.getContNodo()>0){

                if (plataforma.getDato().compareTo(pilaTorreC.peek())>0){
                    return;
                }
            }
            pilaTorreA.pop();
            pilaTorreC.push(plataforma);

            presentarTorreA();
            presentarTorreC();
            presentarCantMov();

            if (pilaTorreC.getContNodo() == objetivo && conNumMov==numMinMov){
                JOptionPane.showMessageDialog(null,"Felicidades hasa alcanzado el objetivo minimo de movimientos","FELICITACIONES",JOptionPane.WARNING_MESSAGE);
            } else if (pilaTorreC.getContNodo()==objetivo && conNumMov !=numMinMov) {
                JOptionPane.showMessageDialog(null,"Felicidades lo haz resuelto\n\nIntenta superar el objetivo minimo de movimientos","FELICITACIONES",JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }catch (Exception E){
        System.out.println("Error: "+E.getMessage());
    }
}
private void moverDeB_C(){
    try {
        if (pilaTorreB.getContNodo()>0){
            Nodo plataforma=new Nodo();
            plataforma.setDato(pilaTorreB.peek());

            if (pilaTorreC.getContNodo()>0){

                if (plataforma.getDato().compareTo(pilaTorreC.peek())>0){
                    return;
                }
            }
            pilaTorreB.pop();
            pilaTorreC.push(plataforma);

            presentarTorreB();
            presentarTorreC();
            presentarCantMov();

            if (pilaTorreC.getContNodo() == objetivo && conNumMov==numMinMov){
                JOptionPane.showMessageDialog(null,"Felicidades hasa alcanzado el objetivo minimo de movimientos","FELICITACIONES",JOptionPane.WARNING_MESSAGE);
            } else if (pilaTorreC.getContNodo()==objetivo && conNumMov !=numMinMov) {
                JOptionPane.showMessageDialog(null,"Felicidades lo haz resuelto\n\nIntenta superar el objetivo minimo de movimientos","FELICITACIONES",JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }catch (Exception E){
        System.out.println("Error: "+E.getMessage());
    }
}
private void moverDeB_A(){
    try {
        if (pilaTorreB.getContNodo()>0){
            Nodo plataforma=new Nodo();
            plataforma.setDato(pilaTorreB.peek());

            if (pilaTorreA.getContNodo()>0){

                if (plataforma.getDato().compareTo(pilaTorreA.peek())>0){
                    return;
                }
            }
            pilaTorreB.pop();
            pilaTorreA.push(plataforma);

            presentarTorreA();
            presentarTorreB();
            presentarCantMov();
        }

    }catch (Exception E){
        System.out.println("Error: "+E.getMessage());
    }
}
private void moverDeC_A(){
    try {
        if (pilaTorreC.getContNodo()>0){
            Nodo plataforma=new Nodo();
            plataforma.setDato(pilaTorreC.peek());

            if (pilaTorreA.getContNodo()>0){

                if (plataforma.getDato().compareTo(pilaTorreA.peek())>0){
                    return;
                }
            }
            pilaTorreC.pop();
            pilaTorreA.push(plataforma);

            presentarTorreA();
            presentarTorreC();
            presentarCantMov();
        }

    }catch (Exception E){
        System.out.println("Error: "+E.getMessage());
    }
}
private void moverDeC_B(){
    try {
        if (pilaTorreC.getContNodo()>0){
            Nodo plataforma=new Nodo();
            plataforma.setDato(pilaTorreC.peek());

            if (pilaTorreB.getContNodo()>0){

                if (plataforma.getDato().compareTo(pilaTorreB.peek())>0){
                    return;
                }
            }
            pilaTorreC.pop();
            pilaTorreB.push(plataforma);

            presentarTorreB();
            presentarTorreC();
            presentarCantMov();
        }

    }catch (Exception E){
        System.out.println("Error: "+E.getMessage());
    }
}

private void moverPlataforma(Pila origen,Pila destino){
    if (Stop==false){
        Nodo plataforma=new Nodo();
        plataforma.setDato(origen.peek());
        origen.pop();

        destino.push(plataforma);

        presentarTorreA();
        presentarTorreB();
        presentarTorreC();
        presentarCantMov();

        JOptionPane pane=new JOptionPane("PASO # "+textFieldNumMov.getText()+"\n\nContinuar?",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
        JDialog dialog=pane.createDialog("Numero de pasos");

        dialog.setLocation(600,400);
        dialog.setVisible(true);

        int opt=(int)pane.getValue();

        if (opt==JOptionPane.NO_OPTION){
            Stop=true;
        }

    }
}


private void resolverHanoiRecur(int n,Pila origen,Pila aux,Pila dest){
    if (n==1){
        moverPlataforma(origen,dest);
    }else{
        resolverHanoiRecur(n-1,origen,dest,aux);
        moverPlataforma(origen,dest);

            resolverHanoiRecur(n-1,aux,origen,dest);
    }
}
}
