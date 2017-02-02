package mx.egm.type;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MyKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener{

    private KeyboardView kv;
    private Keyboard keyboard;
    private boolean caps = false;
    int type=0;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        switch (keyCode) {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);
        String[] keyCode={"97","98","99","100","101","102","103","104","105","106","107","108","109","110","111","112","113","114","115","116","117","118","119","120","121","122"};
        String[] codes={"\uDDE6","\uDDE7","\uDDE8","\uDDE9","\uDDEA","\uDDEB","\uDDEC","\uDDED","\uDDEE","\uDDEF","\uDDF0","\uDDF1","\uDDF2","\uDDF3","\uDDF4","\uDDF5","\uDDF6","\uDDF7","\uDDF8","\uDDF9","\uDDFA","\uDDFB","\uDDFC","\uDDFD","\uDDFE","\uDDFF"};
        String[] codes0={"\uD83C\uDDE6\u0000","\ud83c\udde7\u0000","\ud83c\udde8\u0000","\uD83C\uDDE9\u0000","\uD83C\uDDEA\u0000","\uD83C\uDDEB\u0000","\uD83C\uDDEC\u0000","\uD83C\uDDED\u0000","\uD83C\uDDEE\u0000","\uD83C\uDDEF\u0000","\uD83C\uDDF0\u0000","\uD83C\uDDF1\u0000","\uD83C\uDDF2\u0000","\uD83C\uDDF3\u0000","\uD83C\uDDF4\u0000","\uD83C\uDDF5\u0000","\uD83C\uDDF6\u0000","\uD83C\uDDF7\u0000","\uD83C\uDDF8\u0000","\uD83C\uDDF9\u0000","\uD83C\uDDFA\u0000","\uD83C\uDDFB\u0000","\uD83C\uDDFC\u0000","\uD83C\uDDFD\u0000","\uD83C\uDDFE\u0000","\uD83C\uDDFF\u0000"};
        String[] codes1={"α","в","¢","∂","є","f","g","н","ι","ʝ","к","ℓ","м","и","σ","ρ","q","я","ѕ","т","υ","ν","ω","χ","у","z"};
        String[] codes2={"ⓐ","ⓑ","ⓒ","ⓓ","ⓔ","ⓕ","ⓖ","ⓗ","ⓘ","ⓙ","ⓚ","ⓛ","ⓜ","ⓝ","ⓞ","ⓟ","ⓠ","ⓡ","ⓢ","ⓣ","ⓤ","ⓥ","ⓦ","ⓧ","ⓨ","ⓩ"};
        String[] codes3={"丹","乃","匚","刀","モ","下","ム","卄","工","Ｊ","Ｋ","ㄥ","爪","れ","口","ㄗ","Ｑ","尺","Ｓ","匕","∪","∨","山","メ","ㄚ","乙"};
        ArrayList<String[]> codesList=new ArrayList<>();
        codesList.add(codes0);
        codesList.add(codes1);
        codesList.add(codes2);
        codesList.add(codes3);
        HashMap<String, String> keyCodeMap;
        String inputText=ic.getTextBeforeCursor(1,0).toString();
        switch (primaryCode){
            case Keyboard.KEYCODE_DELETE:
                if(Arrays.asList(codes).contains(inputText)){
                    ic.deleteSurroundingText(1, 0);
                } else if(inputText.equals("\u0000") || inputText.equals("\u0020")){
                    ic.deleteSurroundingText(1, 0);
                    ic.deleteSurroundingText(1, 0);
                }
                ic.deleteSurroundingText(1, 0);
                break;
            case 32:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("32", "  ");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case 97:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("97", "\uD83C\uDDE6\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 98:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("98", "\ud83c\udde7\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 99:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("99", "\ud83c\udde8\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 100:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("100", "\uD83C\uDDE9\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 101:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("101", "\uD83C\uDDEA\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 102:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("102", "\uD83C\uDDEB\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 103:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("103", "\uD83C\uDDEC\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 104:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("104", "\uD83C\uDDED\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 105:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("105", "\uD83C\uDDEE\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 106:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("106", "\uD83C\uDDEF\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 107:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("107", "\uD83C\uDDF0\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 108:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("108", "\uD83C\uDDF1\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 109:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("109", "\uD83C\uDDF2\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 110:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("110", "\uD83C\uDDF3\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 111:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("111", "\uD83C\uDDF4\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 112:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("112", "\uD83C\uDDF5\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 113:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("113", "\uD83C\uDDF6\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 114:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("114", "\uD83C\uDDF7\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 115:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("115", "\uD83C\uDDF8\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 116:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("116", "\uD83C\uDDF9\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 117:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("117", "\uD83C\uDDFA\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 118:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("118", "\uD83C\uDDFB\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 119:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("119", "\uD83C\uDDFC\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 120:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("120", "\uD83C\uDDFD\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 121:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("121", "\uD83C\uDDFE\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            case 122:
                keyCodeMap = new HashMap<String, String>();
                keyCodeMap.put("122", "\uD83C\uDDFF\u0000");
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)), 1);
                break;
            default:
                char code = (char) primaryCode;
                if(Character.isLetter(code) && caps) {
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code), 1);
        }
    }

    public void onText(CharSequence text) {}
    public void swipeLeft() {}
    public void swipeRight() {}
    public void swipeDown() {}
    public void swipeUp() {}
    public void onPress(int primaryCode) {}
    public void onRelease(int primaryCode) {}
}