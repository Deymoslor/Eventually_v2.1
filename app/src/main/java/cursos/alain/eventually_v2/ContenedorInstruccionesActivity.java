package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import cursos.alain.eventually_v2.Adapters.OnboardingAdapter;
import cursos.alain.eventually_v2.entidades.OnboardingItem;

public class ContenedorInstruccionesActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_instrucciones2);

        layoutOnboardingIndicators = findViewById(R.id.layoutObBoardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnBoardingAction);

        setupOnboardingItems();

        final ViewPager2 onboardingViewPager = findViewById(R.id.onBoardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });
        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }else{
                    startActivity(new Intent(getApplicationContext(),Drawer_principal.class));
                    finish();
                }
            }
        });
    }

    private void setupOnboardingItems(){

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemBienvenido = new OnboardingItem();
        itemBienvenido.setImage(R.drawable.bienvenido);

        OnboardingItem itemContinuacion = new OnboardingItem();
        itemContinuacion.setImage(R.drawable.continuacion);

        OnboardingItem itemBuscarGrupos = new OnboardingItem();
        itemBuscarGrupos.setImage(R.drawable.buscargruposview);

        OnboardingItem itemMisGrupos = new OnboardingItem();
        itemMisGrupos.setImage(R.drawable.misgruposview);

        OnboardingItem itemMapa = new OnboardingItem();
        itemMapa.setImage(R.drawable.mapa);

        OnboardingItem itemMicuenta = new OnboardingItem();
        itemMicuenta.setImage(R.drawable.micuenta);

        OnboardingItem itemConfig = new OnboardingItem();
        itemConfig.setImage(R.drawable.configuracionview);

        onboardingItems.add(itemBienvenido);
        onboardingItems.add(itemContinuacion);
        onboardingItems.add(itemBuscarGrupos);
        onboardingItems.add(itemMisGrupos);
        onboardingItems.add(itemMapa);
        onboardingItems.add(itemMicuenta);
        onboardingItems.add(itemConfig);
        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive)
                        );
            }
        }
        if (index == onboardingAdapter.getItemCount()-1){
            buttonOnboardingAction.setText("Inicio");
        }else{
            buttonOnboardingAction.setText("Siguiente");
        }
    }
}