package net.latinus.spirit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;

public class FoodDeliverAdapter  extends RecyclerView.Adapter<FoodDeliverAdapter.ViewHolder>{




    private Context mContext;
    private ArrayList<FoodModel> mList;

    public ArrayList<ProductoList> getmProdList() {
        return mProdList;
    }

     ArrayList<ProductoList> mProdList = new ArrayList<ProductoList>();


    String[] producto=new String[100];
    String[] cantidad=new String[100];
    String[] precio=new String[100];


    public String[] getProducto() {
        return producto;
    }

    public String[] getCantidad() {
        return cantidad;
    }

    public String[] getPrecio() {
        return precio;
    }

    ElegantNumberButton botonAumenta;

    public FoodDeliverAdapter(Context context, ArrayList<FoodModel> list){
        mContext = context;
        mList = list;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.rv_items, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FoodModel contact = mList.get(position);


        // Set item views based on your views and data model
        TextView item_name = holder.item_name;
        TextView item_place = holder.item_place;
        TextView item_price = holder.item_price;
        ImageView item_img = holder.item_image;

        item_name.setText(contact.getItem_name());
        item_place.setText(contact.getItem_place());
        item_price.setText(contact.getItem_price());
        item_img.setImageResource(contact.getItem_image());

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void filterList(ArrayList<FoodModel> filteredList){

        mList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView item_image;
        public TextView item_name,item_place,item_price;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);


//
            item_image = itemView.findViewById(R.id.rv_item_img);
            item_name = itemView.findViewById(R.id.rv_item_name);
            item_place = itemView.findViewById(R.id.rv_item_place);
            item_price = itemView.findViewById(R.id.rv_item_price);
            botonAumenta = (ElegantNumberButton)itemView.findViewById(R.id.botonAumenta);

          /*  botonAumenta.setOnClickListener(new ElegantNumberButton.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String num = botonAumenta.getNumber();
                    Log.e("El producto es: ",item_name.toString());
                    Log.e("Cantidad del producto: ",num);



                    producto[getAdapterPosition()]=item_name.toString();
                    cantidad[getAdapterPosition()]=num;

                }
            });

            */

          botonAumenta.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
              @Override
              public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {

                  String val=view.getNumber();
                  String prod=item_name.getText().toString();
                  String prec=item_price.getText().toString();

                  String s=prec.substring(1);

                  Log.e("prod:",prod);
                  Log.e("Cant de prod:",val);
                  Log.e("Precio prod:",s);

                   /* producto[getAdapterPosition()]=prod;
                    cantidad[getAdapterPosition()]=val;


                    */
                   ProductoList objProd=new ProductoList(prod,val,s);

                 mProdList.add(objProd);




              }
          });


        }
    }
}
