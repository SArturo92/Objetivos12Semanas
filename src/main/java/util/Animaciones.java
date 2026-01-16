/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import vista.DesingJPanelAddTactica;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.swing.SwingUtilities;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

/**
 *
 * @author Sergio Arturo
 */
public final class Animaciones {
    
    private static final SwingTimerTimingSource TIMING_SOURCE = new SwingTimerTimingSource();
    

    static {
        TIMING_SOURCE.init();
    }

    
    
    private Animaciones() {
    
        //TIMING_SOURCE.init();
    }
    

    
    
    public static void animarFadeIn(DesingJPanelAddTactica panel, Runnable casiAlFinal) {
        panel.setAlpha(0f);
        panel.setVisible(true);

        final boolean[] ejecutado = {false};

        Animator animator = new Animator.Builder(TIMING_SOURCE)
                .setDuration(450, TimeUnit.MILLISECONDS)
                .addTarget(new TimingTargetAdapter() {

                    @Override
                    public void timingEvent(Animator source, double fraction) {
                        panel.setAlpha((float) fraction);

                        // 🔥 Ejecutar al 90%
                        if (!ejecutado[0] && fraction >= 0.9) {
                            ejecutado[0] = true;
                            SwingUtilities.invokeLater(casiAlFinal);
                        }
                    }
                })
                .build();

        animator.start();
    }

   
    public static void animarFadeOut(DesingJPanelAddTactica panel, long id, Consumer<Long> onFinish) {
        panel.setAlpha(1f);

        Animator animator = new Animator.Builder(TIMING_SOURCE)
                .setDuration(250, TimeUnit.MILLISECONDS)
                .addTarget(new TimingTargetAdapter() {

                    @Override
                    public void timingEvent(Animator source, double fraction) {
                        panel.setAlpha(1f - (float) fraction);

                        if (fraction > 0.1) {
                            panel.getTxtNomTactica().setVisible(false);
                        }
                    }

                    @Override
                    public void end(Animator source) {
                        panel.setAlpha(0f);
                        onFinish.accept(id); // 👈 callback
                    }
                })
                .build();

        animator.start();
    }
  
   
}
